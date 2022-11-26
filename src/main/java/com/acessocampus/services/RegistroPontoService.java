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
import java.io.IOException;
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
        JSONObject json = readJsonFromUrl("http://worldtimeapi.org/api/timezone/America/Sao_Paulo");
        String str = json.getString("datetime");
        str = str.replace("T"," ");
        str = str.substring(0, 19);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        RegistroPonto registro = new RegistroPonto();
        
        if(dto.getEntradaSaida().equals("E")){
            registro.setEntrada(dateTime);
        }
        
        if(dto.getEntradaSaida().equals("S")){
            registro.setSaida(dateTime);
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
}
