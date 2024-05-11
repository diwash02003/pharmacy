package com.texas.team1.pharmacy.utility;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import com.texas.team1.pharmacy.dto.LocationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class PoijiUtils {

    public List<LocationDto> readData(MultipartFile multipartFile)throws IOException{
        InputStream stream = multipartFile.getInputStream();
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(0).build();
        //        List<LocationDto> locationDtoList =
//                Poiji.fromExcel(stream, PoijiExcelType.XLSX,LocationDto.class,options);
        return Poiji.fromExcel(stream, PoijiExcelType.XLSX,LocationDto.class,options);
    }
}
