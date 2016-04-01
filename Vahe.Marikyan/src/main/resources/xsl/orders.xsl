<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="purchases">
        <html>
            <body>
                <h3>Order Details</h3>
                <xsl:for-each select="purchase">
                <table border="1" style="margin:0 0 10 0" width="50%">
                    <tr bgcolor="#F5F5DC">
                        <th colspan="2" width="50%"><span style="float:left">Ordered on <xsl:value-of select="orderDate"/></span></th>
                        <th colspan="3"><span style="float:right">Order# <xsl:value-of select="@purchaseOrderNumber"/> </span></th>
                    </tr>
                    <tr bgcolor="#9fc4c6">
                        <th width="40%">Item Name</th>
                        <th width="10%">Size</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Condition</th>
                    </tr>
                    <xsl:for-each select="items/item">
                        <tr bgcolor="#F5F5DC">
                            <td><xsl:value-of select="itemName"/></td>
                            <td><xsl:value-of select="size"/></td>
                            <td>$<xsl:value-of select="price"/></td>
                            <td><xsl:value-of select="quantity"/></td>
                            <td><xsl:value-of select="condition"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <table border="1" width="50%">
                    <tr bgcolor="#F5F5DC">
                        <th><span style="float:left" width="50%">Status: <xsl:value-of select="status"/></span></th>
                        <th colspan="2"><span style="float:right">Tracking N: <xsl:value-of select="trackingNumber"/> </span></th>
                    </tr>
                    <tr bgcolor="#9fc4c6">
                        <th width="50%">Shipping Address</th>
                        <th>Order Summary</th>
                        <th>cost</th>
                    </tr>
                    <tr bgcolor="#F5F5DC">
                        <td>
                            <xsl:for-each select="address[@type = 'shipping']">
                                <xsl:value-of select="firstName"/><br/>
                                <xsl:value-of select="lastName"/><br/>
                                <xsl:value-of select="country"/><br/>
                                <xsl:value-of select="city"/><br/>
                                <xsl:value-of select="street"/><br/>
                                <xsl:value-of select="phone"/><br/>
                            </xsl:for-each>
                        </td>
                        <td>
                            Item Subtotal:<br/>
                            Shipping: <br/>
                            Grand Total: <br/>
                        </td>
                        <td>
                            $<xsl:value-of select="orderSummary/subTotal"/><br/>
                            $<xsl:value-of select="orderSummary/shipping"/><br/>
                            <xsl:choose>
                                <xsl:when test="orderSummary/total &gt; 50">
                                    <span style="color:red">$<xsl:value-of select="orderSummary/total"/></span><br/>
                                </xsl:when>
                                <xsl:otherwise>
                                    $<xsl:value-of select="orderSummary/total"/><br/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                    </tr>
                </table>
                    <hr width="50%" style="float:left; margin:20 0"/>
                </xsl:for-each>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
