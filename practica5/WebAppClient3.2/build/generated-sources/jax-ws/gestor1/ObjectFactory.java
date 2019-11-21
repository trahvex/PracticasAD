
package gestor1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gestor1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListImages_QNAME = new QName("http://gestor1/", "ListImages");
    private final static QName _ListImagesResponse_QNAME = new QName("http://gestor1/", "ListImagesResponse");
    private final static QName _ModifyImage_QNAME = new QName("http://gestor1/", "ModifyImage");
    private final static QName _ModifyImageResponse_QNAME = new QName("http://gestor1/", "ModifyImageResponse");
    private final static QName _RegisterImage_QNAME = new QName("http://gestor1/", "RegisterImage");
    private final static QName _RegisterImageResponse_QNAME = new QName("http://gestor1/", "RegisterImageResponse");
    private final static QName _SearchbyAuthor_QNAME = new QName("http://gestor1/", "SearchbyAuthor");
    private final static QName _SearchbyAuthorResponse_QNAME = new QName("http://gestor1/", "SearchbyAuthorResponse");
    private final static QName _SearchbyCreaDate_QNAME = new QName("http://gestor1/", "SearchbyCreaDate");
    private final static QName _SearchbyCreaDateResponse_QNAME = new QName("http://gestor1/", "SearchbyCreaDateResponse");
    private final static QName _SearchbyId_QNAME = new QName("http://gestor1/", "SearchbyId");
    private final static QName _SearchbyIdResponse_QNAME = new QName("http://gestor1/", "SearchbyIdResponse");
    private final static QName _SearchbyKeywords_QNAME = new QName("http://gestor1/", "SearchbyKeywords");
    private final static QName _SearchbyKeywordsResponse_QNAME = new QName("http://gestor1/", "SearchbyKeywordsResponse");
    private final static QName _SearchbyTitle_QNAME = new QName("http://gestor1/", "SearchbyTitle");
    private final static QName _SearchbyTitleResponse_QNAME = new QName("http://gestor1/", "SearchbyTitleResponse");
    private final static QName _Download_QNAME = new QName("http://gestor1/", "download");
    private final static QName _DownloadResponse_QNAME = new QName("http://gestor1/", "downloadResponse");
    private final static QName _DownloadResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gestor1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link ListImagesResponse }
     * 
     */
    public ListImagesResponse createListImagesResponse() {
        return new ListImagesResponse();
    }

    /**
     * Create an instance of {@link ModifyImage }
     * 
     */
    public ModifyImage createModifyImage() {
        return new ModifyImage();
    }

    /**
     * Create an instance of {@link ModifyImageResponse }
     * 
     */
    public ModifyImageResponse createModifyImageResponse() {
        return new ModifyImageResponse();
    }

    /**
     * Create an instance of {@link RegisterImage }
     * 
     */
    public RegisterImage createRegisterImage() {
        return new RegisterImage();
    }

    /**
     * Create an instance of {@link RegisterImageResponse }
     * 
     */
    public RegisterImageResponse createRegisterImageResponse() {
        return new RegisterImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthor }
     * 
     */
    public SearchbyAuthor createSearchbyAuthor() {
        return new SearchbyAuthor();
    }

    /**
     * Create an instance of {@link SearchbyAuthorResponse }
     * 
     */
    public SearchbyAuthorResponse createSearchbyAuthorResponse() {
        return new SearchbyAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDate }
     * 
     */
    public SearchbyCreaDate createSearchbyCreaDate() {
        return new SearchbyCreaDate();
    }

    /**
     * Create an instance of {@link SearchbyCreaDateResponse }
     * 
     */
    public SearchbyCreaDateResponse createSearchbyCreaDateResponse() {
        return new SearchbyCreaDateResponse();
    }

    /**
     * Create an instance of {@link SearchbyId }
     * 
     */
    public SearchbyId createSearchbyId() {
        return new SearchbyId();
    }

    /**
     * Create an instance of {@link SearchbyIdResponse }
     * 
     */
    public SearchbyIdResponse createSearchbyIdResponse() {
        return new SearchbyIdResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywords }
     * 
     */
    public SearchbyKeywords createSearchbyKeywords() {
        return new SearchbyKeywords();
    }

    /**
     * Create an instance of {@link SearchbyKeywordsResponse }
     * 
     */
    public SearchbyKeywordsResponse createSearchbyKeywordsResponse() {
        return new SearchbyKeywordsResponse();
    }

    /**
     * Create an instance of {@link SearchbyTitle }
     * 
     */
    public SearchbyTitle createSearchbyTitle() {
        return new SearchbyTitle();
    }

    /**
     * Create an instance of {@link SearchbyTitleResponse }
     * 
     */
    public SearchbyTitleResponse createSearchbyTitleResponse() {
        return new SearchbyTitleResponse();
    }

    /**
     * Create an instance of {@link Download }
     * 
     */
    public Download createDownload() {
        return new Download();
    }

    /**
     * Create an instance of {@link DownloadResponse }
     * 
     */
    public DownloadResponse createDownloadResponse() {
        return new DownloadResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "ListImages")
    public JAXBElement<ListImages> createListImages(ListImages value) {
        return new JAXBElement<ListImages>(_ListImages_QNAME, ListImages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "ListImagesResponse")
    public JAXBElement<ListImagesResponse> createListImagesResponse(ListImagesResponse value) {
        return new JAXBElement<ListImagesResponse>(_ListImagesResponse_QNAME, ListImagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "ModifyImage")
    public JAXBElement<ModifyImage> createModifyImage(ModifyImage value) {
        return new JAXBElement<ModifyImage>(_ModifyImage_QNAME, ModifyImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "ModifyImageResponse")
    public JAXBElement<ModifyImageResponse> createModifyImageResponse(ModifyImageResponse value) {
        return new JAXBElement<ModifyImageResponse>(_ModifyImageResponse_QNAME, ModifyImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "RegisterImage")
    public JAXBElement<RegisterImage> createRegisterImage(RegisterImage value) {
        return new JAXBElement<RegisterImage>(_RegisterImage_QNAME, RegisterImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "RegisterImageResponse")
    public JAXBElement<RegisterImageResponse> createRegisterImageResponse(RegisterImageResponse value) {
        return new JAXBElement<RegisterImageResponse>(_RegisterImageResponse_QNAME, RegisterImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyAuthor")
    public JAXBElement<SearchbyAuthor> createSearchbyAuthor(SearchbyAuthor value) {
        return new JAXBElement<SearchbyAuthor>(_SearchbyAuthor_QNAME, SearchbyAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyAuthorResponse")
    public JAXBElement<SearchbyAuthorResponse> createSearchbyAuthorResponse(SearchbyAuthorResponse value) {
        return new JAXBElement<SearchbyAuthorResponse>(_SearchbyAuthorResponse_QNAME, SearchbyAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyCreaDate")
    public JAXBElement<SearchbyCreaDate> createSearchbyCreaDate(SearchbyCreaDate value) {
        return new JAXBElement<SearchbyCreaDate>(_SearchbyCreaDate_QNAME, SearchbyCreaDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyCreaDateResponse")
    public JAXBElement<SearchbyCreaDateResponse> createSearchbyCreaDateResponse(SearchbyCreaDateResponse value) {
        return new JAXBElement<SearchbyCreaDateResponse>(_SearchbyCreaDateResponse_QNAME, SearchbyCreaDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyId")
    public JAXBElement<SearchbyId> createSearchbyId(SearchbyId value) {
        return new JAXBElement<SearchbyId>(_SearchbyId_QNAME, SearchbyId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyIdResponse")
    public JAXBElement<SearchbyIdResponse> createSearchbyIdResponse(SearchbyIdResponse value) {
        return new JAXBElement<SearchbyIdResponse>(_SearchbyIdResponse_QNAME, SearchbyIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyKeywords")
    public JAXBElement<SearchbyKeywords> createSearchbyKeywords(SearchbyKeywords value) {
        return new JAXBElement<SearchbyKeywords>(_SearchbyKeywords_QNAME, SearchbyKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyKeywordsResponse")
    public JAXBElement<SearchbyKeywordsResponse> createSearchbyKeywordsResponse(SearchbyKeywordsResponse value) {
        return new JAXBElement<SearchbyKeywordsResponse>(_SearchbyKeywordsResponse_QNAME, SearchbyKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyTitle")
    public JAXBElement<SearchbyTitle> createSearchbyTitle(SearchbyTitle value) {
        return new JAXBElement<SearchbyTitle>(_SearchbyTitle_QNAME, SearchbyTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "SearchbyTitleResponse")
    public JAXBElement<SearchbyTitleResponse> createSearchbyTitleResponse(SearchbyTitleResponse value) {
        return new JAXBElement<SearchbyTitleResponse>(_SearchbyTitleResponse_QNAME, SearchbyTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Download }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "download")
    public JAXBElement<Download> createDownload(Download value) {
        return new JAXBElement<Download>(_Download_QNAME, Download.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestor1/", name = "downloadResponse")
    public JAXBElement<DownloadResponse> createDownloadResponse(DownloadResponse value) {
        return new JAXBElement<DownloadResponse>(_DownloadResponse_QNAME, DownloadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = DownloadResponse.class)
    public JAXBElement<byte[]> createDownloadResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_DownloadResponseReturn_QNAME, byte[].class, DownloadResponse.class, ((byte[]) value));
    }

}
