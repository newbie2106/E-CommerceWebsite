package com.tth.pojo;

import com.tth.pojo.Provinces;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-28T20:14:39")
@StaticMetamodel(AdministrativeRegions.class)
public class AdministrativeRegions_ { 

    public static volatile SingularAttribute<AdministrativeRegions, String> codeNameEn;
    public static volatile SingularAttribute<AdministrativeRegions, String> name;
    public static volatile SingularAttribute<AdministrativeRegions, String> codeName;
    public static volatile SingularAttribute<AdministrativeRegions, Integer> id;
    public static volatile SingularAttribute<AdministrativeRegions, String> nameEn;
    public static volatile SetAttribute<AdministrativeRegions, Provinces> provincesSet;

}