package io.swagger;

import java.io.Serializable;
import java.util.Random;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.LongType;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class CustomIbanGenerator extends SequenceStyleGenerator {
    // return string is of format: "NL\\d{2}\\INHO\\0\\d{9}"
    Random random = new Random();

    public static final String COUNTRY_CODE_PARAMETER = "countryCode";
    public static final String COUNTRY_CODE_DEFAULT = "NL";
    private String countryCode;

    public static final String CONTROL_FORMAT_PARAMETER = "accountCode";
    public static final String CONTROL_FORMAT_DEFAULT = "%02d";
    private String controlCode;

    public static final String BANK_CODE_PARAMETER = "bankCode";
    public static final String BANK_CODE_DEFAULT = "INHO0";
    private String bankCode;

    public static final String ACCOUNT_FORMAT_PARAMETER = "accountCode";
    public static final String ACCOUNT_FORMAT_DEFAULT = "%09d";
    private String accountCode;

    private SharedSessionContractImplementor session;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        this.session = session;
        return countryCode + String.format(controlCode, super.generate(session, object)) + bankCode
                + String.format(accountCode, super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        countryCode = ConfigurationHelper.getString(COUNTRY_CODE_PARAMETER, params, COUNTRY_CODE_DEFAULT);
        controlCode = ConfigurationHelper.getString(CONTROL_FORMAT_PARAMETER, params, CONTROL_FORMAT_DEFAULT);
        bankCode = ConfigurationHelper.getString(BANK_CODE_PARAMETER, params, COUNTRY_CODE_DEFAULT);
        accountCode = ConfigurationHelper.getString(ACCOUNT_FORMAT_PARAMETER, params, ACCOUNT_FORMAT_DEFAULT);
    }
}