/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.personapi.dto.request;

import one.digitalinnovation.personapi.entity.*;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    
    
    private Long id;
    
    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;
    
    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;
    
    @NotEmpty
    @CPF
    private String cpf;
    
    private LocalDate birthDate;
    
    @NotEmpty
    @Valid
    private List<PhoneDTO> phones;
    
    
    
}
