<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		
	<xsl:template match="/">
		<html>
			<head>
				<style>
					.cell {
						padding:3px;
						border:1px solid #c1c1c1;
					}
				</style>
			</head>
			<body>
				<h1>User Defined Expressions</h1>
				
				<xsl:for-each select="expressions/expression">
					<div style="border:1px solid #c3c3c3; margin:30px auto; padding:15px;">
						<xsl:for-each select="alias/text">
							<span style="font-size:20px; color: #006600; font-weight:bold;"><xsl:value-of select="."/> / </span>
						</xsl:for-each> 
						<div>Expression = <span style="font-weight:bold;"><xsl:value-of select="value"/></span></div>
						<table cellpadding="0" cellspacing="0" style="border:1px solid #c1c1c1;">
							<tbody>
								<xsl:for-each select="context/contextItem/filters/filter">
									<tr>
										<td class="cell">category</td>
										<td class="cell"><xsl:value-of select="categoryId"/></td>
									</tr>
									<tr>
										<td class="cell">values</td>
										<td class="cell"><xsl:value-of select="values"/></td>
									</tr>
								</xsl:for-each>
								<xsl:for-each select="context/contextItem/filters/operator">
									<tr>
										<td class="cell">operator</td>
										<td class="cell"><xsl:value-of select="."/></td>
									</tr>
								</xsl:for-each>
							</tbody>
						</table>
						
						<div>
						<div>Owner of the expression is <i><xsl:value-of select="sharingInfo/owner/name"/></i></div>
							<xsl:choose>
								<xsl:when test="sharingInfo/publicShared = 'true'">
									<p>Expression is shared with all users.</p>
								</xsl:when>
								<xsl:otherwise>
									<p>Expression is shared with the following users:</p>
									<ul>
										<xsl:for-each select="sharingInfo/participants/participant">
											<li><i><xsl:value-of select="name"/></i></li>
										</xsl:for-each>
									</ul>
								</xsl:otherwise>
							</xsl:choose>
						</div>
					</div>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>