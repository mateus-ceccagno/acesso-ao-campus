package com.acessocampus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.RegistroPontoMapper;
import com.acessocampus.dto.request.RegistroPontoDTO;
import com.acessocampus.dto.request.RegistroPontoDTOGet;
import com.acessocampus.entities.RegistroPonto;
import com.acessocampus.exceptions.RegistroPontoNotFoundException;
import com.acessocampus.exceptions.PortalNotFoundException;
import com.acessocampus.exceptions.PessoaNotFoundException;
import com.acessocampus.repositories.RegistroPontoRepository;
import com.acessocampus.repositories.PortalRepository;
import com.acessocampus.repositories.PessoaRepository;
import static com.acessocampus.worldtimeapi.JsonReader.readJsonFromUrl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

@Service
public class RegistroPontoService {
    
    private RegistroPontoRepository registroPontoRepository;
    private PessoaRepository pessoaRepository;
    private PortalRepository portalRepository;

    private RegistroPontoMapper registroPontoMapper;

    public RegistroPontoService(
        RegistroPontoRepository registroPontoRepository, 
        RegistroPontoMapper registroPontoMapper,
        PessoaRepository pessoaRepository,
        PortalRepository portalRepository
    ) {
        this.registroPontoRepository = registroPontoRepository;
        this.registroPontoMapper = registroPontoMapper;
        this.pessoaRepository = pessoaRepository;
        this.portalRepository = portalRepository;
    }
    
    public List<RegistroPontoDTOGet> listAll() {
        List<RegistroPonto> registrosPonto = registroPontoRepository.findAll();

        return registrosPonto.stream().map(registroPontoMapper::toDTO).collect(Collectors.toList());
    }

    public RegistroPontoDTOGet findById(Long id) throws RegistroPontoNotFoundException {
        RegistroPonto registroPonto = registroPontoRepository.findById(id).orElseThrow(() -> new RegistroPontoNotFoundException(id));
        return registroPontoMapper.toDTO(registroPonto);
    }

    public void delete(Long id) throws RegistroPontoNotFoundException {
        registroPontoRepository.findById(id).orElseThrow(() -> new RegistroPontoNotFoundException(id));
        registroPontoRepository.deleteById(id);
    }

    public void create(RegistroPontoDTO dto) throws PessoaNotFoundException, PortalNotFoundException, IOException {      
        LocalDateTime horaAtual = ConsultarTempoApiExterna();
        float temperaturaAtual = ConsultarTemperaturaApiColega();
        
        RegistroPonto registro = new RegistroPonto();
        
        if(dto.getEntradaSaida().equals("E")){
            registro.setEntrada(horaAtual);
        }
        
        if(dto.getEntradaSaida().equals("S")){
            registro.setSaida(horaAtual);
        }
        
        pessoaRepository.findById(dto.getPessoaId()).orElseThrow(() -> new PessoaNotFoundException(dto.getPessoaId()));
        portalRepository.findById(dto.getPortalId()).orElseThrow(() -> new PortalNotFoundException(dto.getPortalId()));

        registro.setId(dto.getId());
        registro.setPessoaId(dto.getPessoaId());
        registro.setPortalId(dto.getPortalId());
        //RegistroPonto registroPonto = registroPontoMapper.toModel(dto);
        registroPontoRepository.save(registro);
    }

    public void update(Long id, RegistroPontoDTO dto) 
        throws RegistroPontoNotFoundException, PessoaNotFoundException, PortalNotFoundException {

        registroPontoRepository.findById(id).orElseThrow(() -> new RegistroPontoNotFoundException(id));
        pessoaRepository.findById(dto.getPessoaId()).orElseThrow(() -> new PessoaNotFoundException(dto.getPessoaId()));
        portalRepository.findById(dto.getPortalId()).orElseThrow(() -> new PortalNotFoundException(dto.getPortalId()));
        
        RegistroPonto updatedRegistroPonto = registroPontoMapper.toModel(dto);
        registroPontoRepository.save(updatedRegistroPonto);
    }
    
    
    /*
        Consome http://worldtimeapi.org/api/timezone/
        Parametros: America/Sao_Paulo
        Retorna: datetime do local informado
    */
    private LocalDateTime ConsultarTempoApiExterna () throws IOException {
        JSONObject json = readJsonFromUrl("http://worldtimeapi.org/api/timezone/America/Sao_Paulo");
        String str = json.getString("datetime");
        str = str.replace("T"," ");
        str = str.substring(0, 19);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }
    
    /*
        Consome http://worldtimeapi.org/api/timezone/
        Parametros: America/Sao_Paulo
        Retorna: datetime do local informado
    */
    private float ConsultarTemperaturaApiColega () throws IOException {

        URL url = new URL ("http://127.0.0.1:5000/externa");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        
        String jsonInputString = "{latitude: -27.805045, longitude: -50.336977}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        
        try(BufferedReader br = new BufferedReader(
          new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

        return 0f;
    }
}
