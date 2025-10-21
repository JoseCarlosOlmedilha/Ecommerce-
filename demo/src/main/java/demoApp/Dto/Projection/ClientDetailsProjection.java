package demoApp.Dto.Projection;

import java.time.LocalDate;

public interface ClientDetailsProjection {

    String getNome();
    String getSexo();
    LocalDate getDataAniversario();
    String getEmail();
    String getTipoStringTelefone();
    String getDdd();
    String getNumero();
    String getRua();
    String getCidade();
    String getUf();
    String getNumeroResidencial();
    String getComplemento();
    String getCep();
    String getBairro();
    
}
