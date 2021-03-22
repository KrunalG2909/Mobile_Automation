/**
 * Created by Nitin D on 2020
 */
package main.java.tataCliq.pageClassLib;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TataCliqVariables {

    public TataCliqVariables() {
    }

    private static ThreadLocal<TataCliqVariables> _threadLocal =
            ThreadLocal.withInitial(TataCliqVariables::new);

    public static TataCliqVariables getInstance() {
        return _threadLocal.get();
    }

    public static void setInstanceAsNull() {
        _threadLocal.set(null);
    }

    private String productName;
    private int productPriceWithDiscount;
    private int productPriceWithoutDiscount;
    private int productDiscount;
    private int productPriceAfterDiscount;
    private int shippingCharges;
    private int totalPayable;

}
