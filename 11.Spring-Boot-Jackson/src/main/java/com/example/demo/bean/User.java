package com.example.demo.bean;

import com.example.demo.config.UserDeserializer;
import com.example.demo.config.UserSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView {
    }

    public interface AllUserFieldView extends UserNameView {
    }

    @JsonView(UserNameView.class)
    private String userName;
    @JsonView(AllUserFieldView.class)
    private int age;
    @JsonView(AllUserFieldView.class)
    private String password;
    @JsonView(AllUserFieldView.class)
    private Date birthday;
}