package es.eoi.mundobancario.service;


import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.CuentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    private CuentaRepository cuentaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CuentaClienteDto> findAll() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaClienteDto> cuentasDto = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            CuentaClienteDto cuentaDto = modelMapper.map(cuenta, CuentaClienteDto.class);
            ClienteDto clienteDto = modelMapper.map(cuenta.getCliente(), ClienteDto.class);
            cuentaDto.setClienteDto(clienteDto);
            cuentasDto.add(cuentaDto);
        }

        return cuentasDto;
    }

    @Override
    public List<CuentaClienteDto> findDeudoras() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaClienteDto> cuentasDto = new ArrayList<>();

        for (Cuenta cuenta: cuentas){
            if(cuenta.getSaldo() < 0){
                CuentaClienteDto cuentaDto = modelMapper.map(cuenta, CuentaClienteDto.class);
                ClienteDto clienteDto = modelMapper.map(cuenta.getCliente(), ClienteDto.class);

                cuentaDto.setClienteDto(clienteDto);
                cuentasDto.add(cuentaDto);
            }
        }

        return cuentasDto;
    }

    @Override
    public CuentaClienteDto findById(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id).get();
        ClienteDto clienteDto = modelMapper.map(cuenta.getCliente(), ClienteDto.class);

        CuentaClienteDto cuentaDto = modelMapper.map(cuenta, CuentaClienteDto.class);
        cuentaDto.setClienteDto(clienteDto);

        return cuentaDto;
    }

    @Override
    public CuentaDto save(Cuenta cuenta) {
        Cuenta cuentaSaved = cuentaRepository.save(cuenta);
        CuentaDto cuentaDto = modelMapper.map(cuentaSaved, CuentaDto.class);

        return cuentaDto;
    }

    @Override
    public CuentaDto update(Cuenta cuenta, Integer id) {
        Cuenta cuentaToUpdate = cuentaRepository.findById(id).get();
        cuentaToUpdate.setAlias(cuenta.getAlias());
        Cuenta cuentaUpdated = cuentaRepository.save(cuentaToUpdate);

        CuentaDto cuentaDto = modelMapper.map(cuentaUpdated, CuentaDto.class);

        return cuentaDto;
    }
}
