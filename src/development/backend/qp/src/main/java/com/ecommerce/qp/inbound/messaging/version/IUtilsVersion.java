package com.ecommerce.qp.inbound.messaging.version;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.resources.Version;

public interface IUtilsVersion {
    Version isNextVersion(IMessage message) throws EcommerceBusinessLogicException, InterruptedException;
    Version nextVersion(Version version) throws EcommerceBusinessLogicException;
}
