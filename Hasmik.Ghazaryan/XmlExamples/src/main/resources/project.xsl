<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
            </head>
            <body>
                <h1> DAD Project </h1>
                <xsl:for-each select="projects/project">
                    <span> Project Title: </span>
                    <div style="background-color:teal;color:white;padding:4px">
                        <xsl:for-each select="title/txt">
                            <span style="font-weight:bold"><xsl:value-of select="."/> </span>
                        </xsl:for-each>
                        <!--<xsl:if test="languageId = 1">
                            <span style="font-weight:bold"><xsl:value-of select="title/txt"/> </span>
                        </xsl:if>-->
                    </div>
                    <span> Description: </span>
                    <div>
                        <xsl:for-each select="description/txt">
                            <xsl:value-of select="."/>
                        </xsl:for-each>
                    </div>
                    <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
                        <p>
                            <xsl:value-of select="startDate"/> - <xsl:value-of select="endDate"/>
                        </p>
                    </div>
                    <span> Financial Data: </span>
                    <span> Commitments: </span>
                    <div>
                        <xsl:for-each select="financialData/commitment">
                            <div>
                                <span> <xsl:value-of select="donor/name"/> - <xsl:value-of select="year"/> - <xsl:value-of select="amount"/>$ </span>
                                Disbursements:
                                <table border="1">
                                    <tr bgcolor="#9acd32">
                                        <th>Impelementer: Role</th>
                                        <th>Amount</th>
                                        <th>Year</th>
                                    </tr>
                                    <xsl:for-each select="disbursements/disbursement">
                                        <tr>
                                            <td><xsl:value-of select="implementer/name"/> : <xsl:value-of select="implementer/role"/></td>
                                            <td><xsl:value-of select="amount"/></td>
                                            <td><xsl:value-of select="year"/></td>
                                        </tr>
                                    </xsl:for-each>
                                </table>
                            </div>
                        </xsl:for-each>
                    </div>
                    <span> Expenditures: </span>
                    <table border="1">
                        <tr bgcolor="#9acd32">
                            <th>Amount</th>
                            <th>Year</th>
                        </tr>
                        <xsl:for-each select="financialData/expenditure">
                            <tr>
                                <td><xsl:value-of select="amount"/></td>
                                <td><xsl:value-of select="year"/></td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>

            </body>

        </html>


    </xsl:template>

</xsl:stylesheet>