package com.tth.pojo;

import com.tth.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-26T18:27:48")
@StaticMetamodel(ForgotPassword.class)
public class ForgotPassword_ { 

    public static volatile SingularAttribute<ForgotPassword, Date> expirationTime;
    public static volatile SingularAttribute<ForgotPassword, Integer> otp;
    public static volatile SingularAttribute<ForgotPassword, Integer> id;
    public static volatile SingularAttribute<ForgotPassword, User> user;

}