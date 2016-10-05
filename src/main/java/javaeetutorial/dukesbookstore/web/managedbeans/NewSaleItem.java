/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * <p>Managed bean used by
 * <code>NewSaleBean</code> bean.</p>
 */
@Named
@SessionScoped
public class NewSaleItem implements Serializable {

    private static final long serialVersionUID = 4650160531351805680L;
    Object item;

    public NewSaleItem() {
    }

    public NewSaleItem(Object anItem) {
        item = anItem;
    }

    public Object getItem() {
        return item;
    }
}
