package it.epicode.GestioneDispositivi.service;

import it.epicode.GestioneDispositivi.exception.DispositiviManutenzioneException;
import it.epicode.GestioneDispositivi.exception.NotFoundException;
import it.epicode.GestioneDispositivi.model.Dipendente;
import it.epicode.GestioneDispositivi.model.Dispositivo;
import it.epicode.GestioneDispositivi.model.DispositivoRequest;
import it.epicode.GestioneDispositivi.model.StatoDispositivo;
import it.epicode.GestioneDispositivi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo>getAllDisposit(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }
    public Dispositivo getDispositivoId(int id){
        return dispositivoRepository.findById(id).orElseThrow(()->new NotFoundException("dispositivo con id = "+ id+"not found"));
    }
    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest)throws DispositiviManutenzioneException {
        Dipendente dipendente=dipendenteService.getDipendenteId(dispositivoRequest.getIdDipendente());
        Dispositivo dispositivo=new Dispositivo( dipendente,dispositivoRequest.getStatoDispositivo(),dispositivoRequest.getTipoDispositivo());
        if( dispositivoRequest.getStatoDispositivo().equals( StatoDispositivo.ASSEGNATO)){
         throw new DispositiviManutenzioneException("dispositivo già asegnato");

        } else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.IN_MANUTENZIONE)) {
            throw new NotFoundException("dispositivo non assegnabile");

        }else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.DISMESSO)){
            throw new DispositiviManutenzioneException("dispositivo dismesso");

        }


        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo refreshDispositivo(int id , DispositivoRequest dispositivoRequest) throws  NotFoundException{
        Dispositivo dispositivo=getDispositivoId(id);
        Dipendente dipendente=dipendenteService.getDipendenteId(dispositivoRequest.getIdDipendente());

        dispositivo.setStatoDispositivo(dispositivoRequest.getStatoDispositivo());
        dispositivo.setDipendente(dipendente);
        dispositivo.setTipoDispositivo(dispositivoRequest.getTipoDispositivo());
        return dispositivoRepository.save(dispositivo);
    }

    public void  delete(int id )throws NotFoundException{
        Dispositivo dispositivo=getDispositivoId(id);
        dispositivoRepository.delete(dispositivo);

    }

}


