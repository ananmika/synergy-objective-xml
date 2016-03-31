<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:element name="html">
            <xsl:element name="body">

                <xsl:for-each select="houses/house">
                    <xsl:sort select="price" order="descending" data-type="number"/>

                    <div>
                        <h2 style="float:left;">
                            <xsl:value-of select="@code"/>
                        </h2>
                        <xsl:choose>
                            <xsl:when test="@sale = 'true'">
                                <img src="images/sale.png"
                                     style="width:50px; float:left; margin-top:15px; margin-left:10px"/>
                            </xsl:when>
                        </xsl:choose>
                    </div>
                    <div style="clear:both"></div>

                    <div style="float:left">
                        <img src="images/{@code}.jpg" style="width:300px"/>
                    </div>
                    <div style="float:left; margin-left:10px">
                        <div>Country:
                            <xsl:value-of select="house_address/country"/>
                        </div>
                        <div>City:
                            <xsl:value-of select="house_address/city"/>
                        </div>
                        <div>State:
                            <xsl:value-of select="house_address/state"/>
                        </div>
                        <div>Road:
                            <xsl:value-of select="house_address/road"/>
                        </div>
                        <div>Postcode:
                            <xsl:value-of select="house_address/postcode"/>
                        </div>
                    </div>
                    <div style="clear:both"></div>

                    <div>
                        <div>Number of bedrooms:
                            <xsl:value-of select="house_datils/bedrooms"/>
                        </div>
                        <div>Number of bathrooms:
                            <xsl:value-of select="house_datils/bathrooms"/>
                        </div>
                        <div>Living Space:
                            <xsl:value-of select="house_datils/living_space"/> sq ft
                        </div>
                        <div>Price:
                            <xsl:choose>
                                <xsl:when test="@sale = 'true'">
                                    <span style="text-decoration:line-through;">$
                                        <xsl:value-of select="price"/>
                                    </span>
                                    <span style="color:#ff0000;">$
                                        <xsl:value-of select="sale_price"/>
                                    </span>
                                </xsl:when>
                                <xsl:otherwise>
                                    $
                                    <xsl:value-of select="price"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </div>
                    </div>

                    <table border="1" cellspacing="0"
                           style="margin-top:20px; margin-bottom:50px; width:600px; text-align: center;">
                        <tr bgcolor="#F3F781">
                            <th>Sellers</th>
                            <th>Contact Information</th>
                            <th>Active</th>
                        </tr>
                        <xsl:for-each select="sellers/seller">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <div>Address:
                                        <xsl:value-of select="address/country"/>
                                        <xsl:value-of select="address/city"/>
                                        <xsl:value-of select="address/state"/>
                                        <xsl:value-of select="address/road"/>
                                    </div>
                                    <xsl:choose>
                                        <xsl:when test="phone/type = 'home'">
                                            <div>
                                                <span style="color:#ff00ff">
                                                    <xsl:value-of select="phone"/>
                                                </span>
                                            </div>
                                        </xsl:when>
                                        <xsl:when test="phone/type = 'mobile'">
                                            <div>
                                                <span style="color:#ff00ff">
                                                    <xsl:value-of select="phone"/>
                                                </span>
                                            </div>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="phone"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <div>Email:
                                        <xsl:value-of select="email"/>
                                    </div>
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="@is_active = 'true'">
                                            <img src="images/is_active.gif"/>
                                        </xsl:when>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>