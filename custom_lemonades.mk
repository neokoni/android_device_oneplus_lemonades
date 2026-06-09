#
# Copyright (C) 2018 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
TARGET_SUPPORTS_OMX_SERVICE := false
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from lemonades device
$(call inherit-product, device/oneplus/lemonades/device.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/custom/config/common_full_phone.mk)

PRODUCT_NAME := custom_lemonades
PRODUCT_DEVICE := lemonades
PRODUCT_MANUFACTURER := OnePlus
PRODUCT_BRAND := OnePlus
PRODUCT_MODEL := LE2101

PRODUCT_GMS_CLIENTID_BASE := android-oneplus

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="OnePlus9R_IND-user 14 UKQ1.230924.001 R.1f0e589-1-3ea7e release-keys" \
    BuildFingerprint=OnePlus/OnePlus9R_IND/OnePlus9R:14/UKQ1.230924.001/R.1f0e589-1-3ea7e:user/release-keys \
    DeviceName=OnePlus9R \
    DeviceProduct=OnePlus9R \
    SystemDevice=OnePlus9R \
    SystemName=OnePlus9R

# FIXME: Temp fix for refresh rate stuck at 60Hz. Test regularly and remove if fixed.
#        The package simply set refresh rate to 60Hz and then back (to 60 or 120) to force a refresh.
PRODUCT_PACKAGES += \
    LemonadesRefreshRateHack
