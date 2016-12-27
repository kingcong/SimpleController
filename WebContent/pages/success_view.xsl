<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <header>
  	<title><xsl:value-of select="view/header/title"/></title>
  </header>
  <body>
    <h2><xsl:value-of select="view/header/title"/></h2>
 
    <form>
    	<xsl:attribute name="name">  
        	<xsl:value-of select="view/body/form/name"></xsl:value-of>  
        </xsl:attribute>  
        <xsl:attribute name="method">
        	<xsl:value-of select="view/body/form/method"></xsl:value-of>  
        </xsl:attribute>  
        <xsl:attribute name="action">
        	<xsl:value-of select="view/body/form/action"></xsl:value-of>  
        </xsl:attribute>

        <xsl:for-each select="view/body/form/textView">
			<p>/
				<xsl:attribute name="name">  
    				<xsl:value-of select="name"></xsl:value-of> 
    			</xsl:attribute> 
    			<xsl:value-of select="value"></xsl:value-of>  
			</p>
			<input>
				<xsl:attribute name="type">  
    				<xsl:value-of select="value"></xsl:value-of> 
    			</xsl:attribute> 
			</input>
   		</xsl:for-each>
    
   		<xsl:for-each select="view/body/form/ButtonView">
			<input>
				<xsl:attribute name="name">  
        			<xsl:value-of select="name"></xsl:value-of> 
        		</xsl:attribute> 
        		<xsl:value-of select="label"></xsl:value-of>  
			</input>
   		</xsl:for-each>

   		<xsl:for-each select="view/body/form/CheckBox">
			<input>
				<xsl:attribute name="name">  
        			<xsl:value-of select="name"></xsl:value-of> 
        		</xsl:attribute> 
        		<xsl:value-of select="value"></xsl:value-of>  
			</input>
   		</xsl:for-each>

    </form>     	
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>