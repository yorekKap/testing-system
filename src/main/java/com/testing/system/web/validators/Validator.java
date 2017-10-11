package com.testing.system.web.validators;

import com.testing.system.exceptions.validation.ValidationException;

import java.util.Collection;
import java.util.Date;

/**
 * Created by yuri on 11.10.17.
 */
public abstract class Validator<T> {

    public abstract void validate(T object) throws ValidationException;

    protected void check(boolean statement) throws ValidationException {
        if (!statement) {
            throw new ValidationException();
        }
    }

    protected boolean isNotNull(Object value){
        return value != null;
    }

    protected boolean isNotEmpty(String value){
        return isNotNull(value) && !value.trim().isEmpty() ;
    }

    protected boolean isMatches(String value, String regex){
        return  isNotNull() && value.matches(regex);
    }

    protected boolean isNotNull(Object... values){
        for(Object obj : values) {
            if(obj == null){
                return false;
            }
        }
        return true;
    }

    protected boolean isNotEmpty(String... values){
        for(String value : values) {
            if (value == null && value.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }


    protected boolean isLengthLess(String value, int length){
        return isNotNull(value) && value.length() < length;
    }

    protected boolean isNotEmpty(Collection<?> value){
        return isNotNull(value) && !value.isEmpty();
    }

    protected boolean isSizeLess(Collection<?> value, int length){
        return isNotNull(value) && value.size() < length;
    }

    protected boolean isGreater(Number value, double min){
        return isNotNull(value) && value.doubleValue() > min;
    }

    protected boolean isLess(Number value, double max){
        return isNotNull(value) && value.doubleValue() < max;
    }

    protected boolean isInRangeIncluding(Number value, double max, double min){
        return isNotNull(value) && value.doubleValue() >= min && value.doubleValue() <= max;
    }

    protected boolean isInRange(Number value, double max, double min){
        return isNotNull(value) && value.doubleValue() > min && value.doubleValue() < max;
    }

    protected boolean isAfter(Date date, Date after){
        return isNotNull(date) && date.after(after);
    }

    protected boolean isBefore(Date date, Date before){
        return isNotNull(date) && date.before(before);
    }

    protected boolean isInRange(Date date, Date min, Date max){
        return isNotNull(date) && date.after(min) && date.before(max);
    }

}
