/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.personapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor        
class PhoneDTO {
    
    
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private PhoneType type;
    
    @NotEmpty
    @Size(min = 13, max = 14)    
    private String number;
    
}
