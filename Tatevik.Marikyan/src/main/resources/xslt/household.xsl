<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>My Household</h2>

                <p>
                    <xsl:value-of select="household/description"/>
                </p>

                <h1>My Household members</h1>

                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Description</th>
                        <th>Image</th>
                    </tr>
                    <xsl:for-each select="household/householdMembers/person">
                        <xsl:sort select="firstName"/>
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            <xsl:choose>
                                <xsl:when test="gender = 'Male'">
                                    <td bgcolor="#ff00ff">
                                        <xsl:value-of select="firstName"/>
                                    </td>
                                    <td bgcolor="#ff00ff">
                                        <xsl:value-of select="lastName"/>
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td bgcolor="#ff0000">
                                        <xsl:value-of select="firstName"/>
                                    </td>
                                    <td bgcolor="#ff0000">
                                        <xsl:value-of select="lastName"/>
                                    </td>
                                </xsl:otherwise>
                            </xsl:choose>
                            <td>
                                <ul>
                                    <xsl:if test="@householdHead">
                                        <li>
                                            <span style="color: green;">I'm the household's head</span>
                                        </li>
                                    </xsl:if>
                                    <li>
                                        <xsl:value-of select="dateOfBirth"/>
                                    </li>
                                    <li>
                                        <xsl:value-of select="age"/>
                                    </li>
                                    <li>
                                        <xsl:value-of select="nib"/>
                                    </li>
                                    <li>
                                        <xsl:value-of select="gender"/>
                                    </li>
                                    <li>
                                        <xsl:value-of select="nationality"/>
                                    </li>
                                </ul>
                            </td>
                            <td>
                                <img src="{firstName/@imgsource}"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>


</xsl:stylesheet>