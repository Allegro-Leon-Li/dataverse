package edu.harvard.iq.dataverse.authorization.providers.oauth2;

import edu.harvard.iq.dataverse.authorization.AuthenticatedUserDisplayInfo;
import edu.harvard.iq.dataverse.authorization.UserRecordIdentifier;
import java.util.List;

/**
 * Describes a single user on a remote IDP that uses OAuth2.
 * Normally generated by {@link AbstractOAuth2Idp}.
 * 
 * @author michael
 */
public class OAuth2UserRecord implements java.io.Serializable {
    
    private final String serviceId;
    
    /** An immutable value, probably a number. Not a username that may change. */
    private final String idInService;

    /** A potentially mutable String that is easier on the eye than a number. */
    private final String username;
    
    private final String accessToken;
    
    private final AuthenticatedUserDisplayInfo displayInfo;
    
    private final List<String> availableEmailAddresses;
    
    public OAuth2UserRecord(String aServiceId, String anIdInService, String aUsername,
                            String anAccessToken,  AuthenticatedUserDisplayInfo aDisplayInfo,
                            List<String> someAvailableEmailAddresses) {
        serviceId = aServiceId;
        idInService = anIdInService;
        username = aUsername;
        accessToken = anAccessToken;
        displayInfo = aDisplayInfo;
        availableEmailAddresses = someAvailableEmailAddresses;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getIdInService() {
        return idInService;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public List<String> getAvailableEmailAddresses() {
        return availableEmailAddresses;
    }
    
    public AuthenticatedUserDisplayInfo getDisplayInfo() {
        return displayInfo;
    }

    @Override
    public String toString() {
        return "OAuth2UserRecord{" + "serviceId=" + serviceId + ", idInService=" + idInService + '}';
    }
    
    public UserRecordIdentifier getUserRecordIdentifier() {
        return new UserRecordIdentifier(serviceId, idInService);
    }
}
