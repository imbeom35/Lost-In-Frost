package io.ssafy.userservice.user.member.entity;

import io.ssafy.userservice.oauth2.enums.AuthProvider;
import io.ssafy.userservice.oauth2.enums.Role;
import io.ssafy.userservice.oauth2.userinfo.OAuth2UserInfo;
import io.ssafy.userservice.user.mycostume.entity.MyCostume;
import jakarta.persistence.*;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(indexes = {
        @Index(name = "idx_member_email", columnList = "member_email"),
        @Index(name = "idx_member_nickname", columnList = "member_nickname"),
        @Index(name = "idx_member_role", columnList = "member_role"),
        @Index(name = "idx_member_is_deleted", columnList = "member_is_deleted")
})
public class Member implements UserDetails {

    @Id
    @Column(name = "member_id")
    @Comment("회원 식별자, UUID")
    private String id;

    @Column(name = "member_email")
    @Comment("회원 이메일")
    private String email;

    @Column(name = "member_nickname")
    @Comment("회원 닉네임")
    private String nickname;

    @Column(name = "member_refresh_token")
    @Comment("회원 리프레시 토큰")
    private String refreshToken;

    @Column(name = "member_image")
    @Comment("회원 이미지")
    private String image;

    @Column(name = "member_password")
    @Comment("회원 비밀번호")
    private String password;

    @Column(name = "member_coin")
    @Comment("회원 보유 코인")
    @ColumnDefault("0")
    private int coin;

    @Column(name = "member_crystal")
    @Comment("회원 보유 크리스탈")
    @ColumnDefault("20")
    private Integer crystal;

    @Column(name = "member_level")
    @Comment("회원 현재 레벨")
    @ColumnDefault("1")
    private Integer level;

    @Column(name = "member_experience")
    @Comment("회원 현재 경험치")
    private int experience;

    @Column(name = "member_message")
    @Comment("회원 현재 상태 메시지")
    private String message;

    @JoinColumn(name = "my_costume_seq", referencedColumnName = "my_costume_seq")
    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("회원이 현재 장착한 코스튬")
    private MyCostume myCostume;

    @Column(name = "member_create_datetime")
    @Comment("회원 등록일자")
    @CreationTimestamp
    private Timestamp createDateTime;

    @Column(name = "member_update_datetime")
    @Comment("회원 정보 수정 일자")
    @UpdateTimestamp
    private Timestamp updateDateTime;

    @Column(name = "member_auth_provider")
    @Comment("회원 플랫폼 제공자 : null, GOOGLE, KAKAO, NAVER")
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "member_role")
    @Comment("회원 권한")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "member_is_deleted")
    @Comment("회원 탈퇴 여부")
    @ColumnDefault(value = "false")
    private boolean isDeleted;

    @Column(name = "member_is_email_verified")
    @Comment("회원 이메일 인증 여부")
    @ColumnDefault(value = "false")
    private boolean isEmailVerified;

    @Column(name = "member_play_count")
    @Comment("회원 플레이 횟수")
    @ColumnDefault(value = "0")
    private int playCount;

    @Column(name = "member_success_count")
    @Comment("회원 성공 횟수")
    @ColumnDefault(value = "0")
    private int successCount;

    @Column(name = "member_rank")
    @Comment("회원 랭킹")
    @ColumnDefault(value = "0")
    private int rank;

    @Column(name = "member_rank_update_datetime")
    @Comment("회원 랭킹 업데이트 일자")
    private Timestamp rankUpdateDateTIme;

    @Column(name = "member_game_play_count")
    @Comment("회원 게임 플레이 횟수")
    @ColumnDefault(value = "0")
    private int gamePlayCount;

    @Column(name = "member_ranking_change")
    @Comment("회원 랭킹 변동")
    @ColumnDefault(value = "'NEW'")
    private String rankingChange;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.name()));
        return roles;

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member update(OAuth2UserInfo oAuth2UserInfo) {
        this.email = oAuth2UserInfo.getEmail();
        this.id = oAuth2UserInfo.getOAuth2Id();
        return this;
    }

    public void updateRefreshToken(String token) {
        this.refreshToken = token;
    }

    public Member setMyCostume (MyCostume myCostume, Member member) {
        member.myCostume = myCostume;
        return member;
    }

    public void setCrystal (int crystal) {
        this.crystal = crystal;
    }

    public void useCrystal (int crystal) {
        this.crystal -= crystal;
    }

    public void useCoin (int coin) {
        this.coin -= coin;
    }

    public void updatePassword (String password) {
        this.password = password;
    }

    public void updateNickname (String nickname) {
        this.nickname = nickname;
    }

    public void deleteMember () {
        this.isDeleted = true;
    }

    public void updateRank(int rank, Timestamp rankUpdateDateTIme, String rankingChange) {
        this.rank = rank;
        this.rankUpdateDateTIme = rankUpdateDateTIme;
        this.rankingChange = rankingChange;
    }

    public void updateGamePlayCount() {
        this.gamePlayCount += 1;
    }

    public void updateMessage(String message) {
        this.message = message;
    }
}
