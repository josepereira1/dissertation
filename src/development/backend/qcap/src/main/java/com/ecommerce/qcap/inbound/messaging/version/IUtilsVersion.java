package com.ecommerce.qcap.inbound.messaging.version;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.messaging.resources.IMessage;
import com.ecommerce.qcap.core.business.resources.Version;

public interface IUtilsVersion {
    Version isNextVersion(IMessage message) throws EcommerceBusinessLogicException, InterruptedException;
    Version nextVersion(Version version) throws EcommerceBusinessLogicException;
}
