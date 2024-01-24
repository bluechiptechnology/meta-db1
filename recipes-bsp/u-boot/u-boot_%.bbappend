inherit python3native

COMPATIBLE_MACHINE:db1 = "db1"
DEPENDS += "openssl-native \
            python3-cryptography-native \
            qtestsign"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
UBOOT_MACHINE = "dragonboard410c_defconfig"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/bluechiptechnology/u-boot-db1.git;branch=main;protocol=https \
           file://boot.cmd \
           file://partition_specification.txt \
           file://bootfiles"
SRCREV = "72c236f0d29b67349a34c4cb5f8a8ee97944f94f"

UBOOT_ENV_SUFFIX:db1 = "scr"
UBOOT_ENV:db1 = "boot"
EXTRA_OEMAKE:append:db1 = " BL31=/dev/null"

do_compile:append:db1() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
    export OPENSSL_MODULES="${STAGING_LIBDIR_NATIVE}/ossl-modules"
    python3 ${BASE_WORKDIR}/qtestsign.py aboot u-boot.elf
    install ${WORKDIR}/partition_specification.txt ${DEPLOY_DIR_IMAGE}
    install -d ${DEPLOY_DIR_IMAGE}/bootfiles1
    cp ${WORKDIR}/bootfiles/* ${DEPLOY_DIR_IMAGE}/bootfiles1
    install u-boot-test-signed.mbn ${DEPLOY_DIR_IMAGE}/bootfiles1
}
