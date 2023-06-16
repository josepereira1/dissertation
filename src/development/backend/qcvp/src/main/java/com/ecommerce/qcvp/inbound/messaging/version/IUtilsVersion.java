package com.ecommerce.qcvp.inbound.messaging.version;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.messaging.resources.IMessage;
import com.ecommerce.qcvp.core.business.resources.Version;

public interface IUtilsVersion {
    Version isNextVersion(IMessage message) throws EcommerceBusinessLogicException, InterruptedException;
    Version nextVersion(Version version) throws EcommerceBusinessLogicException;
}
