package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;

@Data
public class Credential {
	private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    public Credential(
            Integer credentialid,
            String url,
            String username,
            String key,
            String password) {

        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
    }

    public Credential(
            Integer credentialid,
            String url,
            String username,
            String key,
            String password,
            Integer userid) {

        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }
}
