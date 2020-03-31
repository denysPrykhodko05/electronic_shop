package com.epam.prykhodko.wrapper;

import static com.epam.prykhodko.constants.LoggerMessagesConstants.CANNOT_FLUSH_BUFFER;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.CANNOT_FLUSH_OUTPUT_STREAM;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.CANNOT_GET_OUTPUT_STREAM;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.CANNOT_GET_PRINT_WRITER;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.apache.log4j.Logger;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private static final Logger LOGGER = Logger.getLogger(GZipServletResponseWrapper.class);
    private GZipServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter;

    public GZipServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    public void close() throws IOException {
        if (Objects.nonNull(printWriter)){
            printWriter.close();
        }

        if (Objects.nonNull(gzipOutputStream)){
            gzipOutputStream.close();
        }

    }

    @Override
    public void flushBuffer() {
        if (this.printWriter != null) {
            this.printWriter.flush();
        }

        try {
            if (this.gzipOutputStream != null) {
                this.gzipOutputStream.flush();
            }
        } catch (IOException e) {
            LOGGER.info(CANNOT_FLUSH_OUTPUT_STREAM);
        }

        try {
            super.flushBuffer();
        } catch (IOException e) {
            LOGGER.info(CANNOT_FLUSH_BUFFER);
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.printWriter != null) {
            throw new IllegalStateException(CANNOT_GET_OUTPUT_STREAM);
        }

        if (this.gzipOutputStream == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }

        return this.gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.printWriter == null && this.gzipOutputStream != null) {
            throw new IllegalStateException(CANNOT_GET_PRINT_WRITER);
        }

        if (this.printWriter == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }

        return this.printWriter;
    }

    @Override
    public void setContentLength(int len) {
    }
}

