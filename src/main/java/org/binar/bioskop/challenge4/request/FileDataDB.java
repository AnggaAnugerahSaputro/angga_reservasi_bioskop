package org.binar.bioskop.challenge4.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDataDB {

    private String filename;
    private String fileType;
    private byte[] data;
}
