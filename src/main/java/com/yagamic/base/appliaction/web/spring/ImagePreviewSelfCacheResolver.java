package com.yagamic.base.appliaction.web.spring;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.cache.Cache;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class ImagePreviewSelfCacheResolver extends AbstractResourceResolver {
    public static final String RESOLVED_RESOURCE_CACHE_KEY_PREFIX = "resolvedResource:";
    private final Cache cache;

    public ImagePreviewSelfCacheResolver(Cache cache) {
        this.cache = cache;
    }

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
                                               List<? extends Resource> locations, ResourceResolverChain chain) {

        String key = RESOLVED_RESOURCE_CACHE_KEY_PREFIX + requestPath + request.getQueryString();
        Resource resource = this.cache.get(key, Resource.class);

        if (resource != null) {
            if (logger.isTraceEnabled()) {
                logger.trace("Found match");
            }
            return resource;
        }

        resource = chain.resolveResource(request, requestPath, locations);
        if ((resource == null) || !isPreviewRequest(request)) {
            return resource;
        }

        try {
            Resource previewResource = new PreviewImageResource(resource);
            if (!previewResource.exists()) {
                BufferedImage image  = ImageIO.read(resource.getFile());
                int minLenght = Math.min(image.getWidth(), image.getHeight());

                Thumbnails.of(image)
                        .sourceRegion(Positions.CENTER, minLenght, minLenght)
                        .size(120, 120)
                        .toFile(previewResource.getFile());
            }

            return previewResource;
        } catch (IOException e) {
            logger.trace("预览图获取失败", e);
        }

        return resource;
    }

    private boolean isPreviewRequest(HttpServletRequest request) {
        return request.getParameter("preview")!=null;
    }

    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath, List<? extends Resource> locations,
                                            ResourceResolverChain chain) {

        return chain.resolveUrlPath(resourceUrlPath, locations);
    }


    private static final class PreviewImageResource extends FileSystemResource {

        public PreviewImageResource(Resource original)  throws IOException {
            super(Paths.get(original.getFile().getParent(), "120x120",original.getFilename()).toString());

            File dir = this.getFile().getParentFile();

            if(!dir.exists()) {
                Files.createDirectories(dir.toPath());
            }
        }
    }
}