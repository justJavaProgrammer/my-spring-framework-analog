package org.springframework.core.io;

import java.io.File;
import java.net.URI;
import java.net.URL;

public interface Resource extends InputStreamSource {

    long contentLength();

    File getFile();

    String getFileName();

    URI getURI();

    URL getURL();

    default boolean isFile() {
        return getFile().isFile();
    }

    default boolean isReadable() {
        return getFile().exists();
    }
}
