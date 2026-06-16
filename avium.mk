# Avium UI
AVIUM_BUILDTYPE := Unofficial
AVIUM_VERSION_APPEND_TIME_OF_DAY := false
AVIUM_MAINTAINER := Neokoni
AVIUM_FORCE_SET_FAKE_PROP := true
AVIUM_SETTINGS_SOC_MODEL_NAME := "Qualcomm Snapdragon 870"
AVIUM_SETTINGS_DEVICE_CODENAME := "OnePlus 9R"

# Feature
TARGET_FORCE_ENABLE_BLUR := true

# GMS
WITH_GMS := true
TARGET_INCLUDE_GOOGLEIME :=true
TARGET_GOOGLEIME_OVERRIDE_IME := true

# Updater
PRODUCT_PACKAGES += \
    Updater

PRODUCT_COPY_FILES += \
     vendor/avium/prebuilt/common/etc/init/init.avium-updater.rc:$(TARGET_COPY_OUT_SYSTEM_EXT)/etc/init/init.avium-updater.rc
