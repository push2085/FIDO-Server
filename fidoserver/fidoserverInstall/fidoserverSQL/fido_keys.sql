/**
 * Copyright StrongAuth, Inc. All Rights Reserved.
 *
 * Use of this source code is governed by the Gnu Lesser General Public License 2.3.
 * The license can be found at https://github.com/StrongKey/FIDO-Server/LICENSE
 */


CREATE TABLE IF NOT EXISTS fido_keys (
        sid                             tinyint NOT NULL DEFAULT 1,
        did                             smallint(5) NOT NULL DEFAULT 1,
        username                        varchar(256) NOT NULL,
        fkid                            BIGINT(20) NOT NULL,
        userid                          varchar(128) NULL,
        keyhandle                       VARCHAR(512) NOT NULL,
        appid                           VARCHAR(512) NULL,
        publickey                       VARCHAR(512) NULL,
        khdigest                        VARCHAR(512) NULL,
        khdigest_type                   ENUM('SHA256','SHA384','SHA512'),
        transports                      tinyint(4) UNSIGNED NULL,
        attsid                          tinyint(4) NULL,
        attdid                          smallint(5) NULL,
        attcid                          mediumint(20) NULL,
        counter                         INT NOT NULL,
        fido_version                    VARCHAR(45) NULL,
        fido_protocol                   ENUM('U2F','UAF','FIDO2_0') NULL,
        aaguid                          varchar(36) NULL,
        registration_settings           LONGTEXT NULL,
        registration_settings_version   INT(11) NULL,
        create_date                     DATETIME NOT NULL,
        create_location                 VARCHAR(256) NOT NULL,
        modify_date                     DATETIME NULL,
        modify_location                 VARCHAR(256),
        status                          ENUM('Active','Inactive') NOT NULL,
        signature                       VARCHAR(2048) NULL,
                PRIMARY KEY (sid,did,username,fkid),
                index (did, username, keyhandle)
        )
        ENGINE = InnoDB;

/* EOF */
