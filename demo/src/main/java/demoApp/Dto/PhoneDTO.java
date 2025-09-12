package demoApp.Dto;

import demoApp.Entities.Enums.TypePhone;
import lombok.Data;


    @Data
    public class PhoneDTO{

        private TypePhone typePhone;
        private String numero;
        private String ddd;

    
    }

