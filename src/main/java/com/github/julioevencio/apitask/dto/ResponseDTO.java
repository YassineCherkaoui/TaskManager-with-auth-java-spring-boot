package com.github.julioevencio.apitask.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.julioevencio.apitask.dto.utils.LinkUtilDTO;

public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<LinkUtilDTO> links;

	public ResponseDTO() {
		this.links = new ArrayList<>();
	}

	public List<LinkUtilDTO> getLinks() {
		return links;
	}

	public void addLink(LinkUtilDTO link) {
		links.add(link);
	}

}
