package com.github.julioevencio.apitask.dto.utils;

import java.io.Serializable;

public class LinkUtilDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String rel;
	private final String href;

	public LinkUtilDTO(String rel, String href) {
		this.rel = rel;
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}

}
