package com.ecommerce.qct.inbound.messaging.version;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.messaging.resources.IMessage;
import com.ecommerce.qct.core.business.resources.Version;

public interface IUtilsVersion {
    Version isNextVersion(IMessage message) throws EcommerceBusinessLogicException, InterruptedException;
    Version nextVersion(Version version) throws EcommerceBusinessLogicException;
}
