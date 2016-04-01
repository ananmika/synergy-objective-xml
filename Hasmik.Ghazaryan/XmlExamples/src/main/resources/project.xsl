<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:element name="html">
            <xsl:element name="head">
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="h1">XSLT Example</xsl:element>
                <xsl:for-each select="projects/project">
                    <div style="background-color:teal;color:white;padding:4px;width:50%;">
                        <span style="font-weight:bold">
                            1. Project Title
                        </span>
                    </div>
                    <div style="margin-left:20px;margin-bottom:5px;margin-top:5px;font-size:12pt;">
                        <xsl:for-each select="title/txt">
                            <span>
                                <xsl:value-of select="."/> <br/>
                            </span>
                        </xsl:for-each>
                        <!--<xsl:if test="txt/languageId = 1">
                            <span style="font-weight:bold"><xsl:value-of select="title/txt"/> </span>
                        </xsl:if>-->
                    </div>
                    <div style="background-color:teal;color:white;padding:4px;width:50%;">
                        <span style="font-weight:bold">
                            2. Description
                        </span>
                    </div>
                    <div style="margin-left:20px;margin-bottom:5px;margin-top:5px;font-size:12pt;">
                        <xsl:for-each select="description/txt">
                            <xsl:value-of select="."/> <br/>
                        </xsl:for-each>
                    </div>
                    <div style="background-color:teal;color:white;padding:4px;width:50%;">
                        <span style="font-weight:bold">
                            3. Project Duration
                        </span>
                    </div>
                    <div style="margin-left:20px;margin-bottom:5px;margin-top:5px;font-size:12pt;">
                        <span>
                            <xsl:value-of select="startDate"/> -
                            <xsl:value-of select="endDate"/>
                        </span>
                    </div>
                    <div style="background-color:teal;color:white;padding:4px;width:50%;">
                        <span style="font-weight:bold">
                            4. Financial Information
                        </span>
                    </div>
                    <br/>
                    <div style="margin-left:20px;background-color:teal;color:white;padding:4px;width:48.4%;">
                        <span style="font-weight:bold">
                            4.1 Commitments
                        </span>
                    </div>
                    <br/>
                    <div style="margin-left:40px;width:46.8%;">
                        <xsl:for-each select="financialData/commitment">
                            <div>
                                <table border="1" style="width:100%;">
                                    <tr bgcolor="#9acd32">
                                        <th>Donor</th>
                                        <th>Amount</th>
                                        <th>Year</th>
                                        <th>Disbursements</th>
                                    </tr>
                                        <tr>
                                            <td>
                                                <xsl:value-of select="donor/name"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="amount"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="year"/>
                                            </td>
                                            <td>
                                                <table border="1">
                                                    <tr bgcolor="#9acd32">
                                                        <th>Impelementer</th>
                                                        <th>Role</th>
                                                        <th>Amount</th>
                                                        <th>Year</th>
                                                    </tr>
                                                    <xsl:for-each select="disbursements/disbursement">
                                                        <tr>
                                                            <td>
                                                                <xsl:value-of select="implementer/name"/>
                                                            </td>
                                                            <td>
                                                                <xsl:value-of select="implementer/role"/>
                                                            </td>
                                                            <td>
                                                                <xsl:value-of select="amount"/>
                                                            </td>
                                                            <td>
                                                                <xsl:value-of select="year"/>
                                                            </td>
                                                        </tr>
                                                    </xsl:for-each>
                                                </table>
                                            </td>
                                        </tr>
                                </table>
                            </div>
                        </xsl:for-each>
                    </div>
                    <br/>
                    <div style="margin-left:20px;background-color:teal;color:white;padding:4px;width:48.4%;">
                        <span style="font-weight:bold">
                            4.2 Expenditures
                        </span>
                    </div>
                    <br/>
                    <div style="margin-left:40px;width:46.8%;">
                        <table border="1" style="width:100%;">
                            <tr bgcolor="#9acd32">
                                <th>Amount</th>
                                <th>Year</th>
                            </tr>
                            <xsl:for-each select="financialData/expenditure">
                                <tr>
                                    <td>
                                        <xsl:value-of select="amount"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="year"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </div>
                </xsl:for-each>

            </xsl:element>

        </xsl:element>


    </xsl:template>

</xsl:stylesheet>