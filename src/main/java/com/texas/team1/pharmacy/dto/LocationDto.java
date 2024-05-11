package com.texas.team1.pharmacy.dto;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LocationDto {
    @ExcelCellName("Code")
    private  Integer code;
    @ExcelCellName("Name")
    private String name;
    @ExcelCellName("Name Nepali")
    private String nameNepali;
    @ExcelCellName("Parent Code")
    private Integer parentId;
}
