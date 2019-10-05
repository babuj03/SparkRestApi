package uk.revolute.bank.util;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectMapper {

   public static void copyProperties(Object src, Object dest){
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
