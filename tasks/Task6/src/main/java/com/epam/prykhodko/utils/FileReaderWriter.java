package com.epam.prykhodko.utils;

import com.epam.prykhodko.repository.ProductRepository;
import java.io.File;

public interface FileReaderWriter {
    void write(File input, File output);
    void write(ProductRepository product);
    void read(File input, File output);
    com.epam.prykhodko.repository.ProductRepository read();
}
