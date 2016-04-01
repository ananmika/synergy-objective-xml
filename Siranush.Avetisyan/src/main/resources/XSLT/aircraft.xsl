<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:element name="html">
            <xsl:element name="body">

                <xsl:for-each select="aircraft/planes/plane">
                    <div>
                        <h2 style="float:left;">
                            <xsl:value-of select="model"/>
                        </h2>
                    </div>
                    <div style="clear:both"></div>
                    <div style="float:left">
                        <img src="images/{model}.jpg" style="width:300px"/>
                    </div>
                    <div style="float:left; margin-left:10px">
                        <div>From:
                            <xsl:value-of select="from"/>
                        </div>
                        <div>To:
                            <xsl:value-of select="to"/>
                        </div>
                        <div>Start:
                            <xsl:value-of select="start_time"/>
                        </div>
                        <div>End:
                            <xsl:value-of select="end_time"/>
                        </div>
                        <div>Passenger count:
                            <xsl:value-of select="passenger_count"/>
                        </div>

                        <!--<div>Main pilot:
                            <xsl:for-each select="aircraft/persons/person">
                                <xsl:if test="@position='pilot'">
                                    <xsl:value-of select="fullName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div>-->
                    </div>
                    <div style="clear:both"></div>
                </xsl:for-each>

                <div>
                    Main Pilots:
                    <xsl:for-each select="aircraft/persons/person">
                        <xsl:if test="@position='pilot'">
                            <div>
                                <xsl:value-of select="fullName"/>
                            </div>
                        </xsl:if>
                    </xsl:for-each>
                </div>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>