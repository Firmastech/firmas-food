package danieldjgomes.larica.core.config;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper modelMapperDesconto() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, Desconto> stringToDescontoConverter = new AbstractConverter<String, Desconto>() {
            @Override
            protected Desconto convert(String source) {
                UUID descontoId = UUID.fromString(source);
                Desconto desconto = new Desconto();
                desconto.setId(descontoId);
                return desconto;
            }
        };

        modelMapper.addConverter(stringToDescontoConverter);

        return modelMapper;
    }
}
