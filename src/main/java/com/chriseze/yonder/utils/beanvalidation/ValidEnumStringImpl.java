package com.chriseze.yonder.utils.beanvalidation;

import java.util.Collection;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEnumStringImpl implements ConstraintValidator<ValidEnumString, Object> {

    private Class<? extends Enum<?>> enumClass;


    @Override
    public void initialize(ValidEnumString constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }

        boolean valid = isValidObject(value);

        boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());

        if(!valid && isDefaultMessage){

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Invalid ")
                    .append(enumClass.getSimpleName())
                    .append(" specified. Valid values are : ")
                    .append(String.join(" ", getEnumPossibleValues(enumClass)));

            ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate(stringBuilder.toString());

            constraintViolationBuilder.addConstraintViolation();
        }

        return valid;
    }

    private boolean isValidObject(Object value){

        Enum<?>[] enumConstants = enumClass.getEnumConstants();

        boolean valid = true;

        if(value.getClass().isArray()){
//			so it is an array of objects not primitives
            if(!value.getClass().getComponentType().isPrimitive()){

                Object[] values = (Object[]) value;

                for(Object aValue : values){
                    if(aValue == null){
                        continue;
                    }
                    if(!isValidEnumString(aValue.toString(), enumConstants)){
                        valid = false;
                        break;
                    }
                }
            } else {
//				we only validate objects and not primitives
                valid = false;
            }
        } else if (Collection.class.isAssignableFrom(value.getClass())){

            @SuppressWarnings("unchecked")
            Collection<Object> values = (Collection<Object>) value;

            for(Object aValue : values){
                if(aValue == null){
                    continue;
                }
                if(!isValidEnumString(aValue.toString(), enumConstants)){
                    valid = false;
                    break;
                }
            }
        } else {
//			we are assuming is an object
            if(!isValidEnumString(value.toString(), enumConstants)){
                valid = false;
            }
        }

        return valid;
    }

    private boolean isValidEnumString(String value, Enum<?>[] enumConstants) {

        for(Enum<?> anEnum : enumConstants){
            if(value.equals(anEnum.name())){
                return true;
            }
        }

        return false;
    }

    private String[] getEnumPossibleValues(Class<? extends Enum<?>> clazz){

        Enum<?>[] enumConstants = clazz.getEnumConstants();

        String[] enumNames = new String[enumConstants.length];

        for(int x=0; x<enumConstants.length; x++){
            enumNames[x] = enumConstants[x].name();
        }

        return enumNames;
    }
}
