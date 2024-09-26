package com.tth.pojo;

import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.Wards;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-26T18:27:48")
@StaticMetamodel(AdministrativeUnits.class)
public class AdministrativeUnits_ { 

    public static volatile SingularAttribute<AdministrativeUnits, String> codeNameEn;
    public static volatile SetAttribute<AdministrativeUnits, Districts> districtsSet;
    public static volatile SingularAttribute<AdministrativeUnits, String> codeName;
    public static volatile SingularAttribute<AdministrativeUnits, String> fullName;
    public static volatile SingularAttribute<AdministrativeUnits, Integer> id;
    public static volatile SingularAttribute<AdministrativeUnits, String> shortNameEn;
    public static volatile SetAttribute<AdministrativeUnits, Wards> wardsSet;
    public static volatile SingularAttribute<AdministrativeUnits, String> shortName;
    public static volatile SingularAttribute<AdministrativeUnits, String> fullNameEn;
    public static volatile SetAttribute<AdministrativeUnits, Provinces> provincesSet;

}