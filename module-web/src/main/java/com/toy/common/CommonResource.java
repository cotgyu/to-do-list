package com.toy.common;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;


public class CommonResource extends EntityModel<Object> {

    public CommonResource(Object object, Link... links){

        EntityModel.of(object, links);
    }
}
