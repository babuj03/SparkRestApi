package uk.revolute.bank.util;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class ObjectMapper {

   public static void copyProperties(Object src, Object dest){
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
