package danieldjgomes.larica.core.culinaria.service;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaRequestDTO;
import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;
import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.culinaria.repository.CulinariaRepository;
import danieldjgomes.larica.core.usecases.CulinariaUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CulinariaServiceImpl implements CulinariaUseCase {

    private final CulinariaRepository culinariaRepository;
    private final ModelMapper modelMapper;

    public CulinariaResponseDTO createCulinaria(CulinariaRequestDTO request) {
        Culinaria culinaria = modelMapper.map(request, Culinaria.class);
        Culinaria savedCulinaria = culinariaRepository.save(culinaria);
        return modelMapper.map(savedCulinaria, CulinariaResponseDTO.class);
    }

    public Optional<CulinariaResponseDTO> getCulinariaById(UUID id) {
        return culinariaRepository.findById(id)
                .map(culinaria -> modelMapper.map(culinaria, CulinariaResponseDTO.class));
    }

    public List<CulinariaResponseDTO> listAllCulinarias() {
        List<Culinaria> culinarias = culinariaRepository.findAll();
        return culinarias.stream()
                .map(culinaria -> modelMapper.map(culinaria, CulinariaResponseDTO.class))
                .collect(Collectors.toList());
    }


    public Optional<CulinariaResponseDTO> updateCulinaria(UUID id, CulinariaRequestDTO request) {
        return culinariaRepository.findById(id)
                .map(culinaria -> {
                    modelMapper.map(request, culinaria);
                    Culinaria updatedCulinaria = culinariaRepository.save(culinaria);
                    return modelMapper.map(updatedCulinaria, CulinariaResponseDTO.class);
                });
    }

    public void deleteCulinaria(UUID id) {
        culinariaRepository.deleteById(id);
    }

}
