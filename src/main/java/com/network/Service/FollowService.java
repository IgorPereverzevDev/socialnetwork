package com.network.Service;

public interface FollowService {
    /**
     * @param userId must not be {@literal null}.
     * @param userFollowId must not be {@literal null}.
     * @return Returns true in case follows was correct
     */
    boolean follow(Long userId,  Long userFollowId);

    /**
     * @param userId must not be {@literal null}.
     * @param userUnFollowId must not be {@literal null}.
     * @return Returns true in case unFollows was correct
     */
    boolean unFollow(Long userId, Long userUnFollowId);
}
