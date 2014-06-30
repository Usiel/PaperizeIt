<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE Parameters [ 
]>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="title-page" page-width="210mm"
                    page-height="297mm" margin-top="0mm" margin-left="0mm" margin-right="0mm"
                    margin-bottom="0mm" >
                    <fo:region-body margin-bottom="0mm"  margin-left="0mm"  margin-right="0mm" margin-top="0mm"/>
                    <fo:region-before region-name="title-page-header" extent="297mm" background-color="#ff0000"/>
                    <fo:region-after region-name="page-footer" extent="0mm"/>
                </fo:simple-page-master>
                <fo:simple-page-master master-name="all-pages" page-width="210mm"
                    page-height="297mm" margin-top="0mm" margin-left="0mm" margin-right="0mm"
                    margin-bottom="0mm">
                    <fo:region-body margin-bottom="8mm" margin-top="25.5mm" margin-left="15mm" margin-right="15mm" />
                    <fo:region-before region-name="continuation-page-header" extent="15mm" margin-left="15mm" margin-right="15mm" background-color="#ff0000"/>
                    <fo:region-after region-name="page-footer" extent="10mm" background-color="#eeeeee" />
                </fo:simple-page-master>
                <fo:page-sequence-master master-name="my-seq">
                    <fo:single-page-master-reference master-reference="title-page"/>
                    <fo:repeatable-page-master-reference master-reference="all-pages"/>
                </fo:page-sequence-master>
            </fo:layout-master-set>
            <xsl:apply-templates select="paper"/>
        </fo:root>
    </xsl:template>
    <xsl:template match="paper">
        <fo:page-sequence master-reference="my-seq">
            <!-- BEFORE -->
            <fo:static-content flow-name="title-page-header">
                <fo:block></fo:block>
            </fo:static-content>
            <fo:static-content flow-name="continuation-page-header">
               <fo:block margin-left="15mm" margin-top="5mm" color="#ffffff" font-family="Courier">Paperize.it - Your invididual Newspaper on <xsl:value-of select="date" /></fo:block>
            </fo:static-content>
            <!-- AFTER -->
            <fo:static-content flow-name="page-footer">
                <fo:block font-size="8pt" font-family="Helvetica" text-align="center" margin-top="3mm">
                    <fo:inline><xsl:value-of select="date"/>  |
                        Page <fo:page-number/> of <fo:page-number-citation ref-id="terminator"/>
                    </fo:inline>
                </fo:block>
            </fo:static-content>
            <!-- BODY -->
            <fo:flow flow-name="xsl-region-body" font-size="11pt" font-family="Helvetica"
                 >
                
                <fo:table table-layout="fixed" inline-progression-dimension.maximum="210mm" width="100%">
                    <fo:table-column column-width="10mm"/>
                    <fo:table-column column-width="100mm"/>
                    <fo:table-column column-width="90mm"/>
                    <fo:table-column column-width="10mm"/>
                    <fo:table-body>
                        <fo:table-row height="30mm" background-color="#ffffff">
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell>
                                <fo:block>
                                    <fo:external-graphic content-width="100mm">
                                        <xsl:attribute name="src">
                                            <xsl:value-of select="paperizelogo"/>
                                        </xsl:attribute>
                                    </fo:external-graphic>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell>
                                <fo:block margin-top="20mm" font-size="18pt" text-align="right" font-family="Courier"><xsl:value-of select="title" /> </fo:block>
                            </fo:table-cell>
                            <fo:table-cell>
                               <fo:block></fo:block>
                            </fo:table-cell>
                        </fo:table-row>


                        

                    </fo:table-body>
                </fo:table>



                <fo:table table-layout="fixed" inline-progression-dimension.maximum="210mm" width="100%" margin-top="3mm">
           
                    <fo:table-column column-width="210mm"/>

                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block margin-bottom="5mm">
                                    <fo:external-graphic content-height="190mm" height="155mm" width="210mm" content-width="scale-to-fit" scaling="non-uniform">
                                        <xsl:attribute name="src">
                                            <xsl:value-of select="articles/titlearticle/imgsrc"/>
                                        </xsl:attribute>
                                    </fo:external-graphic>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>

                         <fo:table-row>
                            <fo:table-cell>
                                <fo:block font-size="22pt" padding="5mm" color="#000000" margin-left="0mm" margin-right="20mm" font-family="Courier" background-color="#ffffff">
                                    <xsl:value-of select="articles/titlearticle/title"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                       

                    </fo:table-body>
                </fo:table>


                <fo:table table-layout="fixed" margin-top="20mm" font-size="8pt" inline-progression-dimension.maximum="210mm" width="100%" break-after="page" font-family="Courier">
                    <fo:table-column column-width="150mm"/>
                    <fo:table-column column-width="50mm"/>
                    <fo:table-column column-width="10mm"/>
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell background-color="#ffffff" padding="3mm">
                                
                                    <fo:block margin-bottom="0mm">
                                                <fo:external-graphic width="44mm" content-width="scale-to-fit" scaling="uniform">
                                                    <xsl:attribute name="src">
                                                        <xsl:value-of select="recipient/stamp"/>
                                                    </xsl:attribute>
                                                </fo:external-graphic>
                                            </fo:block>
                                            <fo:block>
                                                <xsl:value-of select="recipient/salutation" />
                                            </fo:block>
                                             <fo:block>
                                                <xsl:value-of select="recipient/lastname" />&#160;<xsl:value-of select="recipient/firstname" />
                                            </fo:block>
                                            <fo:block>
                                                <xsl:value-of select="recipient/street" />
                                            </fo:block>
                                            <fo:block>
                                                <xsl:value-of select="recipient/zip" />&#160;<xsl:value-of select="recipient/city" />
                                            </fo:block>
                                            <fo:block>
                                                <xsl:value-of select="recipient/country" />
                                            </fo:block>
                                
                            </fo:table-cell>
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                        </fo:table-row>

                       

                    </fo:table-body>
                </fo:table>



                <!-- page 2 empty -->


                <fo:block break-after="page"></fo:block>
               
                <!-- page 3 index -->

                <fo:block font-size="22pt" font-family="Courier">
                    Your News delivered to you
                </fo:block>
                <fo:block margin-top="5mm">
                    Hi <xsl:value-of select="recipient/firstname" />, we created a newspaper for you! Today you can read great news based on your topic selection: 
                    <xsl:for-each select="topics/topic"><xsl:value-of select="name" />,&#160;</xsl:for-each>
                     If you like or don't the articles you read in this newspaper you can rate this newspaper in your Paperize.it Account. Based on this ratings we will try hard to make it better in future.
                </fo:block>
                 <fo:block margin-top="10mm">
                    <fo:external-graphic width="44mm" content-width="scale-to-fit" scaling="uniform">
                        <xsl:attribute name="src">
                            <xsl:value-of select="signature"/>
                        </xsl:attribute>
                    </fo:external-graphic>
                </fo:block>
                <fo:block font-size="16pt" margin-top="10mm" font-family="Courier">
                    Todays News in your paper
                </fo:block>

                <xsl:for-each select="articles/article">
                    <fo:block margin-top="5mm">
                        &#160;-&#160;<xsl:value-of select="title" />
                    </fo:block>
                </xsl:for-each>


                <fo:block break-after="page"></fo:block>
               
                <!-- page 4 articles index -->
                 <xsl:for-each select="articles/article">
                    <fo:block margin-left="-15mm">
                        <fo:external-graphic content-width="210mm" scaling="non-uniform">
                            <xsl:attribute name="src">
                                <xsl:value-of select="imgsrc"/>
                            </xsl:attribute>
                        </fo:external-graphic>
                    </fo:block>
                     <fo:block margin-top="5mm" font-size="22pt" keep-with-previous="always" font-family="Courier">
                        <xsl:value-of select="title" />
                    </fo:block>
                    <fo:block margin-top="5mm" font-size="11pt" font-style="italic" keep-with-previous="always">
                        <xsl:value-of select="intro" />
                    </fo:block>
                    <fo:block margin-top="5mm" font-size="10pt" linefeed-treatment="preserve" keep-with-previous="always">
                        <xsl:value-of select="content" />
                    </fo:block>

                    <fo:block margin-top="5mm" font-size="8pt" color="#cccccc" keep-with-previous="always">
                        Date:&#160;<xsl:value-of select="date" />&#160;|&#160;
                        Newspaper:&#160;<xsl:value-of select="newspaper" />&#160;|&#160;
                        Source:&#160;<xsl:value-of select="srcurl" />
                    </fo:block>
                    
                    <fo:block break-after="page"></fo:block>

                 </xsl:for-each>


                <fo:block id="terminator" />
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>
    
    
    
</xsl:stylesheet>