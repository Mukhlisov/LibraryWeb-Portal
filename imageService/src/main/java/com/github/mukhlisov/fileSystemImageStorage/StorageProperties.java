package com.github.mukhlisov.fileSystemImageStorage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

	private String dir;

}
