package io.ssafy.gameservice.game.draw.dto.response;

import io.ssafy.gameservice.entity.MyCostume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResDto {

    private Long myCostumeSeq;

    private Long costumeSeq;

    private String costumeName;

    private String costumeImage;

    private String costumeGrade;

    public DrawResDto entityToDto(MyCostume myCostume) {
        this.myCostumeSeq = myCostume.getSeq();
        this.costumeSeq = myCostume.getCostumeSeq();
        this.costumeName = myCostume.getCostumeName();
        this.costumeImage = myCostume.getCostumeImage();
        this.costumeGrade = myCostume.getCostumeGrade();
        return this;
    }

}
