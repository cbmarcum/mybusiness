<g:if test="${results.size() == 0}">
    <h3>No Results</h3>
</g:if>
<g:else>
    <h3>${results.rating}&nbsp;<cb:faReviewStars
            totalStars="5">${results.rating}</cb:faReviewStars></h3>
    <g:each in="${results.reviews}" status="i" var="review">

        <div class="media">
            <div class="media-left">
                <g:if test="${review.profile_photo_url}">

                    <img class="media-object img-circle"
                         src="${review.profile_photo_url}"
                         alt="user photo" width="50" height="50">

                </g:if>
                <g:else>
                    <img class="media-object img-circle"
                         src="${review.profile_photo_url}"
                         alt="no photo" width="50" height="50">
                </g:else>
            </div>

            <div class="media-body">
                <h4 class="media-heading"><cb:faReviewStars
                        totalStars="5">${review.rating}</cb:faReviewStars>&nbsp;<small>${review.relative_time_description}</small>
                </h4>

                <p>
                    ${review.text}
                </p>
            </div>
        </div> <!-- .media -->
    </g:each>
</g:else>
